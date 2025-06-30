package schema.format

import schema.Schema
import java.util.regex.Pattern

class PhoneNumberFormatSchema(
    private val pattern: Pattern? = null,
) : Schema<String> {
    data class PhoneRule(
        val countryCode: String,
        val countryName: String,
        val localPattern: Regex,
        val minLength: Int,
        val maxLength: Int,
    )

    private val rules =
        listOf(
            PhoneRule(
                countryCode = "82",
                countryName = "South Korea",
                localPattern = Regex("""^(1(0[0-9]|1[016789])|2|[3-6][1-5])\d{7,8}$"""),
                minLength = 9,
                maxLength = 10,
            ),
            PhoneRule(
                countryCode = "1",
                countryName = "United States / Canada",
                localPattern = Regex("""^[2-9]\d{2}[2-9]\d{6}$"""), // e.g. 4155552671
                minLength = 10,
                maxLength = 10,
            ),
            PhoneRule(
                countryCode = "81",
                countryName = "Japan",
                localPattern = Regex("""^([789]0\d{8}|[1-9]\d{8,9})$"""), // 모바일: 09012345678, 유선: 0311234567
                minLength = 10,
                maxLength = 11,
            ),
            PhoneRule(
                countryCode = "44",
                countryName = "United Kingdom",
                localPattern = Regex("""^(7\d{9}|(1|2)\d{8,9})$"""), // 7으로 시작하면 모바일
                minLength = 10,
                maxLength = 11,
            ),
            PhoneRule(
                countryCode = "49",
                countryName = "Germany",
                localPattern = Regex("""^([1-9]\d{6,13})$"""), // 일반적 길이 기준
                minLength = 7,
                maxLength = 14,
            ),
            PhoneRule(
                countryCode = "33",
                countryName = "France",
                localPattern = Regex("""^[67]\d{8}$"""), // Mobile: 6x/7x 시작
                minLength = 9,
                maxLength = 9,
            ),
            PhoneRule(
                countryCode = "91",
                countryName = "India",
                localPattern = Regex("""^[6789]\d{9}$"""), // 모바일 번호 시작
                minLength = 10,
                maxLength = 10,
            ),
            PhoneRule(
                countryCode = "86",
                countryName = "China",
                localPattern = Regex("""^1[3-9]\d{9}$"""), // 모바일 번호 시작
                minLength = 11,
                maxLength = 11,
            ),
            PhoneRule(
                countryCode = "61",
                countryName = "Australia",
                localPattern = Regex("""^(4\d{8}|[2378]\d{8,9})$"""), // 4로 시작하면 모바일
                minLength = 9,
                maxLength = 10,
            ),
            // 🇧🇷 Brazil
            PhoneRule(
                countryCode = "55",
                countryName = "Brazil",
                localPattern = Regex("""^([1-9]{2}9\d{8})$"""), // 9로 시작하는 11자리 (지역번호+모바일)
                minLength = 11,
                maxLength = 11,
            ),
        )

    override fun validate(value: Any?) {
        if (value !is String) {
            throw IllegalArgumentException("Expect string")
        }

        if (pattern != null && !Pattern.matches(this.pattern.pattern(), value)) {
            throw java.lang.IllegalArgumentException("format type not match(phone_number), ")
        }

        val countryCodeMap = rules.associateBy { it.countryCode }
        val digits = value.replace(Regex("[^0-9]"), "")

        if (digits.isEmpty()) {
            throw IllegalArgumentException("Phone number contains no digits.")
        }

        val candidate =
            (1..4)
                .map { digits.take(it) }
                .firstOrNull { countryCodeMap.containsKey(it) }
                ?: throw IllegalArgumentException("Invalid country code.")

        val rule = countryCodeMap[candidate] ?: throw IllegalArgumentException("No validation rule for the country code.")
        val nationalNumber = digits.removePrefix(candidate)

        if (nationalNumber.length !in rule.minLength..rule.maxLength) {
            throw IllegalArgumentException("Phone number length does not match the rules for ${rule.countryName}.")
        }

        if (!rule.localPattern.matches(nationalNumber)) {
            throw IllegalArgumentException("Phone number format is invalid for ${rule.countryName}.")
        }
    }
}

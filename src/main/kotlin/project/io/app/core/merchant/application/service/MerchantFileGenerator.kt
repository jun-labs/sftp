package project.io.app.core.merchant.application.service

import org.springframework.stereotype.Component
import java.io.ByteArrayInputStream

@Component
class MerchantFileGenerator {

    fun generateFileContent(
        merchantId: Long,
        subMalls: List<String>,
    ): ByteArrayInputStream {
        val builder = StringBuilder()
        builder.append("Merchant ID, SubMall Data\n")
        subMalls.forEach { builder.append("$merchantId, $it\n") }
        return ByteArrayInputStream(builder.toString().toByteArray())
    }
}

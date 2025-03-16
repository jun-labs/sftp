package project.io.app.core.merchant.application

import java.io.ByteArrayInputStream

interface MerchantQueryUseCase {
    fun downloadFile(fileName: String): ByteArrayInputStream?
}

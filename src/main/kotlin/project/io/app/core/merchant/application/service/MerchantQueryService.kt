package project.io.app.core.merchant.application.service

import org.springframework.stereotype.Component
import project.io.app.core.merchant.application.MerchantQueryUseCase
import project.io.app.core.merchant.external.MerchantSftpClient
import java.io.ByteArrayInputStream

@Component
class MerchantQueryService(
    private val merchantSftpClient: MerchantSftpClient,
) : MerchantQueryUseCase {

    override fun downloadFile(fileName: String): ByteArrayInputStream? {
        return merchantSftpClient.downloadFile(fileName)
    }
}

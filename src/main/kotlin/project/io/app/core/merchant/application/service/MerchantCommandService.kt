package project.io.app.core.merchant.application.service

import org.springframework.stereotype.Service
import project.io.app.core.merchant.application.MerchantCommandUseCase
import project.io.app.core.merchant.domain.MerchantQueryRepository
import project.io.app.core.merchant.external.MerchantSftpClient


@Service
class MerchantCommandService(
    private val merchantQueryRepository: MerchantQueryRepository,
    private val merchantFileGenerator: MerchantFileGenerator,
    private val merchantSftpClient: MerchantSftpClient,
) : MerchantCommandUseCase {

    override fun uploadMerchantSubMall(merchantId: Long) {
        val findSubMalls = merchantQueryRepository.findSubMallByMerchantId(merchantId)

        if (findSubMalls.isEmpty()) {
            return
        }

        val subMallNames = findSubMalls.map { it.name }
        val file = merchantFileGenerator.generateFileContent(merchantId, subMallNames)
        merchantSftpClient.uploadFile(file, "subMalls")
    }
}

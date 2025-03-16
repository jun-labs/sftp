package project.io.app.core.merchant.presentation

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import project.io.app.core.merchant.application.service.MerchantCommandService

@RestController
@RequestMapping(path = ["/api/merchant"])
class MerchantSftpUploadApi(
    private val merchantCommandService: MerchantCommandService,
) {

    @PostMapping(path = ["/{merchantId}/sftp"])
    fun uploadFiles(@PathVariable(name = "merchantId") merchantId: Long) {
        merchantCommandService.uploadMerchantSubMall(merchantId)
    }
}

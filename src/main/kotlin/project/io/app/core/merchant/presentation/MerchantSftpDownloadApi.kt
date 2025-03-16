package project.io.app.core.merchant.presentation

import org.springframework.core.io.ByteArrayResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import project.io.app.core.merchant.application.MerchantQueryUseCase

@RestController
@RequestMapping(path = ["/api/merchant"])
class MerchantSftpDownloadApi(
    private val merchantQueryUseCase: MerchantQueryUseCase,
) {

    @GetMapping(path = ["/sftp"])
    fun downloadMerchantFile(
        @RequestParam fileName: String,
    ): ResponseEntity<ByteArrayResource> {
        val fileStream = merchantQueryUseCase.downloadFile(fileName)
            ?: return ResponseEntity.notFound()
                .build()
        val bytes = fileStream.readBytes()
        if (bytes.isEmpty()) {
            return ResponseEntity.notFound().build()
        }

        val resource = ByteArrayResource(bytes)
        val headers = HttpHeaders().apply {
            contentType = MediaType.parseMediaType("text/csv")
            set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=merchant.csv")
        }
        return ResponseEntity.ok()
            .headers(headers)
            .contentLength(bytes.size.toLong())
            .body(resource)
    }
}

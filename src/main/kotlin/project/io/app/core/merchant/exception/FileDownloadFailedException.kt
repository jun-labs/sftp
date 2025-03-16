package project.io.app.core.merchant.exception

class FileDownloadFailedException : RuntimeException(MESSAGE) {

    companion object {
        private const val MESSAGE = "파일 업로드에 실패했습니다."
    }
}

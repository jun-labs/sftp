package project.io.app.core.merchant.external

import com.jcraft.jsch.ChannelSftp
import com.jcraft.jsch.JSch
import com.jcraft.jsch.Session
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import project.io.app.Logger
import project.io.app.core.merchant.exception.FileDownloadFailedException
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.Properties

@Service
class MerchantSftpClient(
    @Value("\${sftp.host}") private val host: String,
    @Value("\${sftp.port}") private val port: Int,
    @Value("\${sftp.username}") private val username: String,
    @Value("\${sftp.password}") private val password: String,
    @Value("\${sftp.remote-directory}") private val remoteDir: String,
) {

    private val logger = Logger()

    fun downloadFile(fileName: String): ByteArrayInputStream? {
        var session: Session? = null
        var channelSftp: ChannelSftp? = null
        val outputStream = ByteArrayOutputStream()

        try {
            val jsch = JSch()
            session = jsch.getSession(username, host, port)
            session.setPassword(password)
            val config = Properties().apply {
                put("StrictHostKeyChecking", "no")
            }
            session.setConfig(config)
            session.connect()

            channelSftp = session.openChannel("sftp") as ChannelSftp
            channelSftp.connect()

            channelSftp.cd(remoteDir)
            channelSftp.get(fileName, outputStream)
            logger.info("✅ SFTP Download Success: $fileName")
        } catch (ex: Exception) {
            logger.error("❌ SFTP Download Failed: $fileName", ex)
            throw FileDownloadFailedException()
        } finally {
            channelSftp?.disconnect()
            session?.disconnect()
        }
        return ByteArrayInputStream(outputStream.toByteArray())
    }

    fun uploadFile(
        fileContent: ByteArrayInputStream,
        fileName: String,
    ) {
        var session: Session? = null
        var channelSftp: ChannelSftp? = null

        try {
            val jsch = JSch()
            session = jsch.getSession(username, host, port)
            session.setPassword(password)
            val config = Properties().apply {
                put("StrictHostKeyChecking", "no")
            }
            session.setConfig(config)
            session.connect()

            channelSftp = session.openChannel("sftp") as ChannelSftp
            channelSftp.connect()

            channelSftp.cd(remoteDir)
            channelSftp.put(fileContent, fileName)
            logger.info("✅ SFTP Upload Success: $fileName")
        } catch (ex: IOException) {
            logger.error("❌ I/O error during SFTP Upload: $fileName", ex)
        } catch (ex: Exception) {
            logger.error("❌ SFTP Upload Failed: $fileName", ex)
        } finally {
            channelSftp?.disconnect()
            session?.disconnect()
        }
    }
}

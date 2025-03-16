package project.io.app.core.merchant.domain

interface MerchantQueryRepository {
    fun findSubMallByMerchantId(merchantId: Long): List<MerchantSubMall>
}

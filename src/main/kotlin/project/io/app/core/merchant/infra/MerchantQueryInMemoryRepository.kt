package project.io.app.core.merchant.infra

import org.springframework.stereotype.Repository
import project.io.app.core.merchant.domain.MerchantQueryRepository
import project.io.app.core.merchant.domain.MerchantSubMall

@Repository
internal class MerchantQueryInMemoryRepository : MerchantQueryRepository {

    companion object {
        private val subMalls = mutableListOf<MerchantSubMall>()

        init {
            subMalls.add(MerchantSubMall(1, 1, "롯데면세점"))
            subMalls.add(MerchantSubMall(2, 1, "롯데백화점"))
            subMalls.add(MerchantSubMall(3, 1, "롯데시네마"))
            subMalls.add(MerchantSubMall(4, 1, "롯데마트"))

            subMalls.add(MerchantSubMall(5, 2, "삼성전자"))
            subMalls.add(MerchantSubMall(6, 2, "삼성생명"))
            subMalls.add(MerchantSubMall(7, 2, "삼성물산"))

            subMalls.add(MerchantSubMall(8, 3, "현대자동차"))
            subMalls.add(MerchantSubMall(9, 3, "현대백화점"))
            subMalls.add(MerchantSubMall(10, 3, "현대모비스"))
        }
    }

    override fun findSubMallByMerchantId(merchantId: Long): List<MerchantSubMall> {
        return subMalls.filter { it.merchantId == merchantId }
            .toList()
    }
}

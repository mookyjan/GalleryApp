package com.mudassir.domain.usecase

import androidx.paging.PagingData
import com.mudassir.domain.util.SchedulerProvider
import com.mudassir.domain.gateway.ImageListGateWay
import com.mudassir.domain.generateImageEntityList
import com.mudassir.domain.scheduler.TestSchedulers
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.rxjava3.core.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class GetImageListUseCaseTest {

    lateinit var getImageListUseCase: GetImageListUseCase
    private val imageListGateWay: ImageListGateWay = mock()
    lateinit var schedulerProvider: SchedulerProvider

    @Before
    fun setUp() {
        schedulerProvider = TestSchedulers()
        getImageListUseCase = GetImageListUseCase(schedulerProvider, imageListGateWay)
    }

    @Test
    fun `get images list`() {
        //Given
        val mockImageList = generateImageEntityList()
        val pagingData = PagingData.from(mockImageList)
        val response = Flowable.just(pagingData)
        val refresh = true
        Mockito.`when`(imageListGateWay.getImageList(refresh)).thenReturn(eq(response))
        //when
        val testObserver = getImageListUseCase.execute(refresh).test()
        //then
        testObserver.assertComplete()
            .assertNoErrors()
            .assertValue(pagingData)
    }


}
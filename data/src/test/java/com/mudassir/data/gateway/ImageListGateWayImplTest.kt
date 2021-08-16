package com.mudassir.data.gateway

import androidx.paging.PagingData
import com.mudassir.data.repository.ImagesListRepository
import com.mudassir.data.util.TestData
import com.mudassir.domain.gateway.ImageListGateWay
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ImageListGateWayImplTest{

    @Mock
    private lateinit var imageListRepository: ImagesListRepository
    private lateinit var imageListGateWay: ImageListGateWay
    val pageId =1
    val refresh = true

    @Before
    fun setup() {
        imageListGateWay = ImageListGateWayImpl(imageListRepository)
    }

    @Test
    fun `Given image List data,when get image list response,Should fetch data from repository and then parse to domain`() {
        //Given
        val domainData = TestData.generateImageEntityList()
        val repositoryData = TestData.generateImageList()

        val pagingData = PagingData.from(domainData)
        val response = Flowable.just(pagingData)
        
        Mockito.`when`(imageListRepository.getImagesList(refresh,pageId))
            .thenReturn(Single.just(repositoryData))

        //when
        val testObserver = imageListGateWay.getImageList(refresh).test().await()

        //then
        testObserver.assertComplete()
            .assertNoErrors()
            .assertValue(pagingData)
    }
}
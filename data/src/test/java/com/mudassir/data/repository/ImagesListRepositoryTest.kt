package com.mudassir.data.repository

import com.mudassir.data.remote.GetImagesListRemoteDataSource
import com.mudassir.data.util.TestData
import io.reactivex.rxjava3.core.Single
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class ImagesListRepositoryTest{

    @Mock
    private lateinit var remoteDataSource: GetImagesListRemoteDataSource
    private lateinit var imageListRepository: ImagesListRepository
    val pageId =1

    @Before
    fun setup(){
        imageListRepository = ImagesListRepository(remoteDataSource)
    }

    @Test
    @Throws(Exception::class)
    fun `get list of images response success will return the list`(){

        //Given
        Mockito.`when`(remoteDataSource.getImagesList(pageId)).thenReturn(Single.just(TestData.generateImageList()))

        //When
        val testObserver = imageListRepository.getImagesList(true,pageId).test().await()

        //should
        testObserver.assertComplete()
            .assertNoErrors()
            .assertValue(TestData.generateImageList())
    }

    @Test
    @Throws(Exception::class)
    fun `getImagesList returns null if response is null`(){

        //Given
        val response = TestData.getTestImageList()
        Mockito.`when`(remoteDataSource.getImagesList(pageId)).thenReturn(null)

        //when
        val testObserver = imageListRepository.getImagesList(true,pageId)

        //should
        assertEquals(testObserver,null)
    }
}
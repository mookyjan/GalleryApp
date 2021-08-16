package com.mudassir.data.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.mudassir.data.mapper.ImageListMapper
import com.mudassir.data.repository.ImagesListRepository
import com.mudassir.domain.entity.ImageItemEntity
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException

class ImagesDataSource(val imagesListRepository: ImagesListRepository) :
    RxPagingSource<Int, ImageItemEntity>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, ImageItemEntity>> {
        val page = params.key ?: 1
        return try {
            val response = imagesListRepository.getImagesList(false, page)
                .subscribeOn(Schedulers.io())
            response.map {
                val mapResponse = ImageListMapper.transformFromList(it)

                toLoadResult(mapResponse, page)
            }.onErrorReturn {
                LoadResult.Error(it)
            }

        } catch (exception: IOException) {
            Single.just(LoadResult.Error(exception))
        } catch (exception: HttpException) {
            Single.just(LoadResult.Error(exception))
        } catch (exception: InvalidObjectException) {
            Single.just(LoadResult.Error(exception))
        } catch (exception: Exception) {
            Single.just(LoadResult.Error(exception))
        }

    }

    private fun toLoadResult(
        data: List<ImageItemEntity>,
        position: Int
    ): LoadResult<Int, ImageItemEntity> {

        return LoadResult.Page(
            data = data ?: listOf(),
            prevKey = if (position == 1) null else position - 1,
            nextKey = position + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, ImageItemEntity>): Int? {
       return state.anchorPosition
    }

}
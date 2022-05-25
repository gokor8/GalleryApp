package com.example.data.repository

import android.provider.ContactsContract
import com.example.data.api.models.pictures.RequestPictureModel
import com.example.data.api.models.pictures.ResponsePictureInfoModel
import com.example.data.core.photos.PhotosServerState
import com.example.data.datasource.pictures.PicturesDataSource
import com.example.data.datasource.pictures.PicturesDataSourceImpl
import com.example.domain.core.UiFailModel
import com.example.domain.core.handle_factories.HandleFactory
import com.example.domain.entities.photos.ShowPicturesInfo
import com.example.domain.entities.states.PhotosState
import junit.framework.JUnit4TestAdapter
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MediaRepositoryTest {

    @Test
    fun `load pictures success`() {
        runBlocking {
            val repository = MediaRepositoryImpl(TestPictureDataSource(true))

            val actual = repository.loadPictures(ShowPicturesInfo(0, 0))
            val expected = PhotosServerState.Success::class

            Assert.assertTrue(actual is PhotosState.Success)
        }
    }

    @Test
    fun `load pictures error`() {
        runBlocking {
            val repository = MediaRepositoryImpl(TestPictureDataSource(false))

            val actual = repository.loadPictures(ShowPicturesInfo(0, 0))
            val expected = PhotosServerState.NoData::class

            Assert.assertTrue(actual is PhotosState.Fail)
        }
    }

    class TestPictureDataSource(private val isSuccess: Boolean) : PicturesDataSource {
        override suspend fun getPictures(requestPictureModel: RequestPictureModel): PhotosServerState {
            return if (isSuccess) {
                PhotosServerState.Success(
                    arrayOf(
                        ResponsePictureInfoModel(
                            0,
                            null,
                            null,
                            true,
                            false,
                            ResponsePictureInfoModel.ResponsePictureModel(0, "test"),
                            null
                        )
                    )
                )
            } else {
                PhotosServerState.NoData(TestHandleFactory())
            }
        }
    }

    class TestHandleFactory : HandleFactory<PhotosServerState, UiFailModel> {
        override fun handle(e: PhotosServerState): UiFailModel = TestUiFailModel()
    }

    class TestUiFailModel : UiFailModel {
        override fun getMessageResId(): Int = 0

        override fun getMessage(): String = "test"
    }
}
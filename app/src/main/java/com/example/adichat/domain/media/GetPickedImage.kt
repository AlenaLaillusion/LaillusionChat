package com.example.adichat.domain.media

import android.graphics.Bitmap
import android.net.Uri
import com.example.adichat.domain.interactor.UseCase
import com.example.adichat.domain.type.Either
import com.example.adichat.domain.type.Failure
import javax.inject.Inject

class GetPickedImage @Inject constructor(
    private val mediaRepository: MediaRepository
) : UseCase<Bitmap, Uri?>() {

    override suspend fun run(params: Uri?): Either<Failure, Bitmap> = mediaRepository.getPickedImage(params)
}
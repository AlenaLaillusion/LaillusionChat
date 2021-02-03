package com.example.adichat.data.media

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import com.example.adichat.domain.media.MediaRepository
import com.example.adichat.domain.type.Either
import com.example.adichat.domain.type.Failure
import java.io.File

class MediaRepositoryImpl(val context: Context) : MediaRepository {
    override fun createImageFile(): Either<Failure, Uri> {
        val uri :Uri? = MediaHelper.createImageFile(context)
        return if (uri == null) {
            Either.Left(Failure.FilePickError)
        } else {
            Either.Right(uri)
        }
    }

    override fun encodeImageBitmap(bitmap: Bitmap?): Either<Failure, String> {
        if (bitmap == null) return Either.Left(Failure.FilePickError)

        val imageString :String = MediaHelper.encodeToBase64(bitmap, Bitmap.CompressFormat.JPEG, 100)

        return if (imageString.isEmpty()) {
            Either.Left(Failure.FilePickError)
        } else {
            Either.Right(imageString)
        }
    }

    override fun getPickedImage(uri: Uri?): Either<Failure, Bitmap> {
        if (uri == null) return Either.Left(Failure.FilePickError)

        val filePath :String? = MediaHelper.getPath(context, uri)
        val image :Bitmap? = MediaHelper.saveBitmapToFile(File(filePath))

        return if (image == null) {
            Either.Left(Failure.FilePickError)
        } else {
            Either.Right(image)
        }

    }
}
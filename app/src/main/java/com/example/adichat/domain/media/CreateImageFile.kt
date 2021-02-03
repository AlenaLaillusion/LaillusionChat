package com.example.adichat.domain.media

import android.net.Uri
import com.example.adichat.domain.interactor.UseCase
import com.example.adichat.domain.type.Either
import com.example.adichat.domain.type.Failure
import com.example.adichat.domain.type.None
import javax.inject.Inject

class CreateImageFile @Inject constructor(
    private val mediaRepository: MediaRepository
) : UseCase<Uri, None>() {
    override suspend fun run(params: None): Either<Failure, Uri> = mediaRepository.createImageFile()
}
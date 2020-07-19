package lanka.meeting.app.di

import lanka.meeting.app.repository.MainRepository
import lanka.meeting.app.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    single { MainRepository(get()) }
    viewModel { MainViewModel(get()) }

}

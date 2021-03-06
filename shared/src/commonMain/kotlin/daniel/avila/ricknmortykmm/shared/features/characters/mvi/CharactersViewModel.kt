package daniel.avila.ricknmortykmm.shared.features.characters.mvi

import daniel.avila.ricknmortykmm.shared.base.mvi.BaseViewModel
import daniel.avila.ricknmortykmm.shared.base.mvi.BasicUiState
import daniel.avila.ricknmortykmm.shared.base.mvi.UiEffect
import daniel.avila.ricknmortykmm.shared.domain.interactors.GetCharactersUseCase
import org.koin.core.component.inject

open class CharactersViewModel : BaseViewModel<CharactersContract.Event, CharactersContract.State, UiEffect>() {
    private val getCharactersUseCase: GetCharactersUseCase by inject()

    override fun createInitialState(): CharactersContract.State = CharactersContract.State(characters = BasicUiState.None)

    override fun handleEvent(event: CharactersContract.Event) {
        when (event) {
            CharactersContract.Event.OnGetCharacters -> getCharacters()
        }
    }

    private fun getCharacters() {
        setState { copy(characters = BasicUiState.Loading) }
        launch(getCharactersUseCase.execute(), { data ->
            setState { copy(characters = BasicUiState.Success(data)) }
        }, {
            setState { copy(characters = BasicUiState.Error()) }
        })
    }
}
import { createSlice } from '@reduxjs/toolkit'
import { Languages as ILanguages } from '../../app/types'
export const languageSlice = createSlice({
  name: 'language',
  initialState: {
    value: ILanguages.English,
  },
  reducers: {
    switchLanguage: (state) => {
      state.value = state.value === ILanguages.English ? ILanguages.Spainnish : ILanguages.English;
      },
  },
})

// Action creators are generated for each case reducer function
export const {switchLanguage} = languageSlice.actions

export default languageSlice.reducer


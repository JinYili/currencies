import { configureStore } from '@reduxjs/toolkit'
import languageReducer from './slice/themeSlice'
 
const store = configureStore({
  reducer: {
    language:languageReducer
  }
})
export default store;
export type AppStore = typeof store
export type RootState = ReturnType<AppStore['getState']>
export type AppDispatch = AppStore['dispatch']
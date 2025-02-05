 
import Header from '../app/components/Header'
import { expect, test } from 'vitest'
import { render, screen } from '@testing-library/react'
import store from '../redux/theme'
import { Provider } from 'react-redux'  
 
 
 
test('Header', () => {
  render (<Provider store={store} > <Header languageHelper={()=>{}}></Header></Provider> )
  const contentResult = screen.findByText('Currency Exchange'); 
  expect(contentResult).to.exist;
})
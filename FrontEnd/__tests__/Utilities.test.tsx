import { expect, test } from 'vitest'
import {  calculateValue,
    isNumeric} from '../app/utilities'




    test('IsNumericFalse', () => {
        expect(isNumeric('de')).toBe(false)
      })

    test('IsNumericTrue', () => {
        expect(isNumeric('555')).toBe(true)
      })


    test('calculateValueFalse', () => {
        expect(calculateValue(2,6.45).toString()).toBe("12.90")
      })
 
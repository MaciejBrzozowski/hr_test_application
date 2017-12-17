package pl.brzozowski.maciej.clis.utilities

import spock.lang.Specification
import spock.lang.Unroll

import static pl.brzozowski.maciej.clis.utilities.StringCalculations.divide

@Unroll
class StringCalculationsSpecTest extends Specification {

    def "should return calculated value"() {
        expect:
        result == divide(equation)

        where:
        equation   | result
        "5000/127" | 39.37007874015748
        "0.0001"   | 0.0001
    }

}
package pl.brzozowski.maciej.clis.utilities

import spock.lang.Specification

class FindIContentSpecTest extends Specification {
    FindIContent findIContent

    def "test findStringByRegex"() {
        setup:
        findIContent = new FindIContent()

        expect:
        result == findIContent.findStringByRegex(response, regex)
        where:
        result | regex                         | response
        "123"  | "1.0 meters  =  (.*?) inches" | "1.0 meters  =  123 inches"
    }
}

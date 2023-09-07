// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
class MySuite extends munit.FunSuite {
  test("example test that succeeds") {
    val newValue = 42
    val someOtherVal = 42
    assertEquals(newValue, someOtherVal)

    print("Some text!")
  }
}

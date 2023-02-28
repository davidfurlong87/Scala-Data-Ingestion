package exceptions

class CustomExceptions {
  class PathException(message: String) extends Exception
  class MissingValuesException(message: String) extends Exception
}

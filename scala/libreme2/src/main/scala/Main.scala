import canoe.api._
import canoe.syntax._
import cats.effect.Async
import cats.effect.IO
import cats.effect.Sync
import fs2.Stream
import scala.concurrent.Future
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.FileWriter
import cats.effect.IOApp
import cats.effect.ExitCode

import fs2.io.file.{Path, Files}
import fs2.{Stream, text}

def token = sys.env.get("TG_TOKEN")
object MainApp extends IOApp {
  def run(args: List[String]): IO[ExitCode] = app[IO].map(_ => ExitCode.Success)
}

def app[F[_]: Async]: F[Unit] =
  for {

    env <- Files[IO].readUtf

    _ <- Stream
        .resource(TelegramClient[F](token))
        .flatMap(implicit client => Bot.polling[F].follow(greetings))
        .compile
        .drain


  } yield ()
  
def greetings[F[_]: TelegramClient]: Scenario[F, Unit] =
  for {
    chat <- Scenario.expect(command("hi").chat)
    _ <- Scenario.eval(chat.send("Hello. What's your name?"))
    name <- Scenario.expect(text)
    _ <- Scenario.eval(chat.send(s"Nice to meet you, $name"))
  } yield ()

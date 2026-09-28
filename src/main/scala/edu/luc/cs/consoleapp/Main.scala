package edu.luc.cs.consoleapp

import java.util.Scanner
import org.apache.commons.collections4.queue.CircularFifoQueue

object Main:
  val LAST_N_WORDS = 10

  // Processes words and notifies the callback after each update.
  def processWords(
      input: java.util.Iterator[String],
      lastNWords: Int,
      onUpdate: CircularFifoQueue[String] => Unit
  ): Unit =
    val queue = new CircularFifoQueue[String](lastNWords)

    while input.hasNext do
      val word = input.next()
      queue.add(word)
      onUpdate(queue)

  def main(args: Array[String]): Unit =
    if args.length > 1 then
      System.err.println(
        "usage: ./target/universal/stage/bin/main [ last_n_words ]"
      )
      sys.exit(2)

    var lastNWords = LAST_N_WORDS

    try
      if args.length == 1 then
        lastNWords = args(0).toInt

        if lastNWords < 1 then
          throw new NumberFormatException()
    catch
      case _: NumberFormatException =>
        System.err.println("argument should be a natural number")
        sys.exit(4)

    val input =
      new Scanner(System.in).useDelimiter("(?U)[^\\p{Alpha}0-9']+")

    try
      processWords(
        input,
        lastNWords,
        queue =>
          System.out.println(queue)

          if System.out.checkError() then
            sys.exit(1)
      )
    finally
      input.close()

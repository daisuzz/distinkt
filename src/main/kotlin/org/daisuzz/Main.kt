package org.daisuzz

import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.required
import java.io.File

fun main(args: Array<String>) {
    val parser = ArgParser("distinct")
    val csvFileOption by parser.option(ArgType.String, shortName = "f", description = "CSV file path").required()
    val columnNameOption by parser.option(ArgType.String, shortName = "c", description = "Column name to remove duplicates").required()

    parser.parse(args)

    // CSVファイルを読み込む
    val csvFile = File(csvFileOption)
    val lines = csvFile.readLines()

    // CSVのヘッダ行から指定されたカラムのインデックスを取得する
    val header = lines.first().split(",")
    val columnIndex = header.indexOf(columnNameOption)

    // カラムの値をキーにして行を格納するMapを作成する
    val rows = mutableMapOf<String, String>()

    for (line in lines.drop(1)) {
        val values = line.split(",")
        val key = values[columnIndex]
        if (!rows.containsKey(key)) {
            rows[key] = line
        }
    }

    // 結果を出力する
    println(header.joinToString(","))
    for (row in rows.values) {
        println(row)
    }
}

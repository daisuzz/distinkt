import java.io.File

fun main(args: Array<String>) {
    // 引数の数が適切でない場合はエラーメッセージを出力して終了する
    if (args.size != 2) {
        println("Usage: distinkt <CSVファイル> <カラム名>")
        return
    }

    // 引数からCSVファイル名とカラム名を取得する
    val csvFileName = args[0]
    val column = args[1]

    // CSVファイルを読み込む
    val csvFile = File(csvFileName)
    val lines = csvFile.readLines()

    // CSVのヘッダ行から指定されたカラムのインデックスを取得する
    val header = lines.first().split(",")
    val columnIndex = header.indexOf(column)

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

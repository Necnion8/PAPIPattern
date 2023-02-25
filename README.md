# PAPIPattern
PlaceholderAPIと連携し、正規表現により出力をカスタムする

## 前提
- PlaceholderAPI

## コマンドと権限
- 設定の再読み込みコマンド - `/papiPattern reload`

## 設定
```yml
placeholders:
  "hpdisplay":
    - input: "%player_health%"
      check: "^20\\.0$"
      output: "FULL"
    - output: "%player_health%"

  "ppt_server_(\\w+)":
    - input: "%serverutils_server_online_$1%"
      check: "ONLINE"
      output: "ON-LINE $1"
    - output: "OFF-LINE $1"
```

2つのプレースホルダが初期設定されています。


#### 設定項目について
- `input` : 条件判別に使う文字列 (引数の代入に対応)
- `output` : 出力する文字列 (引数の代入に対応)
- `check` : `input` の条件判別。この判別テストに成功すると出力が確定します。(正規表現に対応)
- これらの設定条件をいくつか設定でき、上から順に判別されていきます。
##### 例: `%ppattern_hpdisplay%`
1. 実行者プレイヤーのHP(`%player_health%`)が `20.0` だった場合に `FULL` という文字を出力します。
2. **1.** の条件がマッチしなかった場合は、`%player_health%` の値がそのまま出力されます。
##### 例: `%ppattern_ppt_server_XXX%`)
0. `%ppattern_ppt_server_abc%` が与えられた場合
1. `input` が `%serverutils_server_online_abc%` に変換され、`ONLINE` を含む値の場合は `ON-LINE abc` を出力します。
2. **1.** の条件がマッチしなかった場合は、`OFF-LINE abc` の値が出力されます。

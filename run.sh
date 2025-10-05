#!/bin/bash

set -e
SRC_DIR="src"
OUT_DIR="tmp"

mkdir -p "$OUT_DIR"

find "$SRC_DIR" -name "*.java" > "$OUT_DIR/sources"
javac -d "$OUT_DIR" @"$OUT_DIR/sources"
rm "$OUT_DIR/sources"

if [ -d "$SRC_DIR/resources" ]; then
    cp -r "$SRC_DIR/resources/"* "$OUT_DIR"/ 2>/dev/null || true
fi

java -cp "$OUT_DIR" game.Main

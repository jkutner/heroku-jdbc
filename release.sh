#!/usr/bin/env bash

set -o pipefail
set -eu

mvn release:prepare release:perform

#!/bin/bash
python -m grpc_tools.protoc -I../proto --python_out=. --grpc_python_out=. ../proto/k8ty_nltk.proto

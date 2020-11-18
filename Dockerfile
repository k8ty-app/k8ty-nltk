FROM python:3.8-buster
ADD proto /app/proto/
ADD python /app/python/
WORKDIR /app/python
RUN pip install -r requirements.txt && python setup.py && sh proto-generate.sh
EXPOSE 50051
CMD ["python", "server.py"]

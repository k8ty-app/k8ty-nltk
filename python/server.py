import grpc
from concurrent import futures
from nltk.corpus import stopwords
from nltk.stem import PorterStemmer
from nltk.tokenize import word_tokenize, sent_tokenize
from k8ty_nltk_pb2 import Response, Language
from k8ty_nltk_pb2_grpc import K8tyNLTKServicer, add_K8tyNLTKServicer_to_server


class K8tyNLTKService(K8tyNLTKServicer):
    ps = PorterStemmer()

    @staticmethod
    def lang(request):
        if request.config.language == Language.english:
            return "english"
        return "english"

    def sentTokenize(self, request, context):
        response = sent_tokenize(request.data[0], self.lang(request))
        return Response(data=response)

    def wordTokenize(self, request, context):
        response = word_tokenize(request.data[0], self.lang(request))
        return Response(data=response)

    def stem(self, request, context):
        result = map(self.ps.stem, request.data)
        return Response(data=result)

    def stopWords(self, request, context):
        return Response(data=set(stopwords.words(self.lang(request))))


def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    add_K8tyNLTKServicer_to_server(
        K8tyNLTKService(), server)
    server.add_insecure_port('[::]:50051')
    server.start()
    server.wait_for_termination()


if __name__ == '__main__':
    serve()

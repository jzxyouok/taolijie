__author__ = 'whf'

import sys
import thread
import time
import BaseHTTPServer
import subprocess
import logging

# configure logging
FORMAT = "%(asctime)-15s  %(user)-8s %(message)s"
CUR_TIME = time.strftime('%Y-%m-%d-%H-%M-%S', time.localtime(time.time()))
logging.basicConfig(filename="/data/taolijie/scripts-log/deploy-server-%s.log" % CUR_TIME, format=FORMAT)


def deploy():
    outcome = subprocess.check_output(('.', '/root/projects/taolijie/server-deploy.sh'))
    logging.info(outcome)
    return outcome


# The request handler
class Handler(BaseHTTPServer.BaseHTTPRequestHandler):
    # handle POST request
    def do_POST(self):
        logging.info('client addr:%s', self.client_address[0])
        logging.info('client port:%s', self.client_address[1])
        logging.info('method:%s', self.command)

        # execute command in a separate thread
        thread.start_new_thread(deploy)

        self.send_response(200)


# start HTTP server
server_addr = ('127.0.0.1', 9001)
httpd = BaseHTTPServer.HTTPServer(server_addr, Handler)
httpd.serve_forever()

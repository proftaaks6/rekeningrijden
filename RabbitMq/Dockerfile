FROM rabbitmq:management
COPY ./conf/ /etc/rabbitmq/
ENV RABBITMQ_ERLANG_COOKIE '7mG7R9HRDAkjfUT3kNCq5KsgVEgbTYR'
RUN ['rabbitmqctl','add_user', 'username', 'password']
RUN ['rabbitmqctl', 'set_user_tags', 'username', 'administrator']
RUN ['rabbitmqctl', 'set_permissions', '-p', '/ username ".*" ".*" ".*"']
CMD ['bash', '-c', '"sleep 10; rabbitmq-server;']
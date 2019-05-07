FROM rabbitmq:management
COPY ./conf/ /etc/rabbitmq/
COPY ./conf/.erlang.cookie /var/lib/rabbitmq/.erlang.cookie

ENV RABBITMQ_ERLANG_COOKIE "'7mG7R9HRDAkjfUT3kNCq5KsgVEgbTYR'"
# Define environment variables.
ENV RABBITMQ_USER username
ENV RABBITMQ_PASSWORD password

ADD init.sh /init.sh
EXPOSE 15672

RUN chmod 777 /init.sh

# Define default command
CMD ["/init.sh"]
FROM fluent/fluentd:v1.16-1

# Use root account to use apk
USER root

# Install plugins and dependencies
RUN apk add --no-cache --update \
    build-base \
    ruby-dev \
    && gem install \
    fluent-plugin-elasticsearch:5.3.0 \
    fluent-plugin-concat:2.5.0 \
    fluent-plugin-record-modifier:2.1.1 \
    fluent-plugin-kubernetes_metadata_filter:3.2.0 \
    && gem sources --clear-all \
    && apk del build-base ruby-dev \
    && rm -rf /tmp/* /var/tmp/* /usr/lib/ruby/gems/*/cache/*.gem

# switch back to fluent user
USER fluent
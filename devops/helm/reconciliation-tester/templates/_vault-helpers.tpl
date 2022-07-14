{{/*
Define vault annotations
*/}}

{{- define "reconciliation-tester.vaultKafka" -}}
vault.hashicorp.com/role: {{ .Release.Namespace | quote }}
vault.hashicorp.com/agent-inject: 'true'
vault.hashicorp.com/agent-inject-status: update
vault.hashicorp.com/agent-inject-secret-kafka-cfg.json: secrets/{{ .Release.Namespace }}/{{ .Release.Name }}_kafka
vault.hashicorp.com/agent-inject-template-kafka-cfg.json: |
  {{ printf "{{- with secret \"secrets/%s/%s_kafka\" -}}" $.Release.Namespace $.Release.Name }}
  {
    "CLIENT_ID":            "{{ printf "{{ .Data.data.clientId }}" }}",
    "KAFKA_TOPIC":          "{{ printf "{{ .Data.data.topic }}" }}",
    "KAFKA_CONSUMER_GROUP": "{{ printf "{{ .Data.data.consumerGroup }}" }}",
    "KAFKA_BROKERS":        "{{ printf "{{ .Data.data.brokers }}" }}"
  }
  {{ printf "{{- end }}" }}
{{- end -}}

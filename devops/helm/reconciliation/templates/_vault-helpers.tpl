{{/*
Define vault annotations
*/}}

{{- define "reconciliation.vaultMongo" -}}
vault.hashicorp.com/role: {{ .Release.Namespace | quote }}
vault.hashicorp.com/agent-inject: 'true'
vault.hashicorp.com/agent-inject-status: update
vault.hashicorp.com/agent-inject-secret-mongodsn.json: secrets/{{ .Release.Namespace }}/{{ .Release.Name }}_mongo
vault.hashicorp.com/agent-inject-template-mongodsn.json: |
  {{ printf "{{- with secret \"secrets/%s/%s_mongo\" -}}" $.Release.Namespace $.Release.Name }}
  {
    "prefix":   "{{ printf "{{ .Data.data.prefix    }}" }}",
    "username": "{{ printf "{{ .Data.data.username  }}" }}",
    "password": "{{ printf "{{ .Data.data.password  }}" }}",
    "host":     "{{ printf "{{ .Data.data.host      }}" }}",
    "database": "{{ printf "{{ .Data.data.database  }}" }}",
    "options":  "{{ printf "{{ .Data.data.options   }}" }}"
  }
  {{ printf "{{- end }}" }}
{{- end -}}

{{- define "reconciliation.vaultKafka" -}}
vault.hashicorp.com/role: {{ .Release.Namespace | quote }}
vault.hashicorp.com/agent-inject: 'true'
vault.hashicorp.com/agent-inject-status: update
vault.hashicorp.com/agent-inject-secret-kafka-cfg.json: secrets/{{ .Release.Namespace }}/{{ .Release.Name }}_kafka
vault.hashicorp.com/agent-inject-template-kafka-cfg.json: |
  {{ printf "{{- with secret \"secrets/%s/%s_kafka\" -}}" $.Release.Namespace $.Release.Name }}
  {
    "KAFKA_CLIENT_ID":    "{{ printf "{{ .Data.data.clientName }}" }}",
    "KAFKA_BROKERS":      "{{ printf "{{ .Data.data.brokers }}" }}"
  }
  {{ printf "{{- end }}" }}
{{- end -}}

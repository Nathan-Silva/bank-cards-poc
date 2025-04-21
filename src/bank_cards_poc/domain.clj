(ns bank-cards-poc.domain)

(defn uuid [] (str (java.util.UUID/randomUUID)))

(defn criar-cliente [nome cpf]
  {:id (uuid)
   :nome nome
   :cpf cpf})

(defn criar-cartao [cliente-id tipo limite]
  {:id (uuid)
   :cliente-id cliente-id
   :tipo (keyword tipo)
   :limite limite
   :disponivel limite
   :status :ativo
   :numero (format "%016d" (rand-int 10000000000000000))
   :validade "04/30"})

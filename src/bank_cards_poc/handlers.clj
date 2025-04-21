(ns bank-cards-poc.handlers
  (:require
    [bank-cards-poc.db :refer [*cartoes *clientes]]
    [bank-cards-poc.domain :as domain]
    [cheshire.core :as json]
    [ring.util.response :refer [response status]]))

(defn parse-body [req]
  (-> req :body slurp (json/parse-string true)))

(defn criar-cliente [req]
  (let [{:keys [nome cpf]} (parse-body req)
        cliente (domain/criar-cliente nome cpf)]
    (println "Cliente criado com sucesso!")
    (swap! *clientes assoc (:id cliente) cliente)
    (-> (response cliente)
        (status 201))))

(defn emitir-cartao [req]
  (let [{:keys [cliente-id tipo limite]} (parse-body req)
        cliente (get @*clientes cliente-id)]
    (if cliente
      (let [cartao (domain/criar-cartao cliente-id tipo limite)]
        (swap! *cartoes update cliente-id conj cartao)
        (-> (response cartao)
            (status 201)))
      (status (response {:erro "Cliente não encontrado"}) 404))))

(defn listar-cartoes [req]
  (let [cliente-id (get-in req [:path-params :id])]
    (if-let [cartoes (get @*cartoes cliente-id)]
      (response cartoes)
      (status (response {:erro "Cliente não encontrado"}) 404))))

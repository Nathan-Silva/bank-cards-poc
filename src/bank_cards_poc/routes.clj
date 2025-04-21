(ns bank-cards-poc.routes
  (:require
    [reitit.ring :as ring]
    [bank-cards-poc.handlers :as h]))

(def app
  (ring/ring-handler
    (ring/router
      [["/clientes" {:post h/criar-cliente}]
       ["/cartoes" {:post h/emitir-cartao}]
       ["/clientes/:id/cartoes" {:get h/listar-cartoes}]])
    (ring/create-default-handler)))

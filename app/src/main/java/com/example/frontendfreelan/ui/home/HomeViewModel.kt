package com.example.frontendfreelan.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _items = MutableLiveData<List<Item>>().apply {
        value = listOf(
            Item("Reparo de Eletrodomésticos", "São Paulo", "Serviços Domésticos", "R$ 200,00", "Reparo de eletrodomésticos como geladeiras, máquinas de lavar, etc."),
            Item("Manutenção de Jardins", "Rio de Janeiro", "Jardinagem", "R$ 150,00", "Corte de grama, podas e cuidados gerais com o jardim."),
            Item("Instalação de Ar-Condicionado", "Belo Horizonte", "Climatização", "R$ 300,00", "Instalação de aparelhos de ar-condicionado em residências."),
            Item("Reforma de Banheiros", "Brasília", "Construção Civil", "R$ 2.500,00", "Reforma completa de banheiros, incluindo revestimento e hidráulica."),
            Item("Aulas de Inglês", "Curitiba", "Educação", "R$ 50,00 por hora", "Aulas particulares de inglês com foco em conversação."),
            Item("Serviço de Babá", "Fortaleza", "Cuidados Infantis", "R$ 100,00 por dia", "Cuidado de crianças durante o período de trabalho dos pais."),
            Item("Fotografia de Eventos", "Salvador", "Fotografia e Vídeo", "R$ 800,00", "Cobertura fotográfica para eventos como casamentos e aniversários."),
            Item("Desenvolvimento de Sites", "Porto Alegre", "Tecnologia da Informação", "R$ 3.000,00", "Criação de sites personalizados para empresas e profissionais."),
            Item("Limpeza Residencial", "Recife", "Serviços Domésticos", "R$ 120,00", "Limpeza geral de residências, incluindo quartos, banheiros e cozinhas."),
            Item("Manutenção de Computadores", "Florianópolis", "Tecnologia da Informação", "R$ 150,00", "Serviços de manutenção e reparo de computadores e notebooks."),
            Item("Aulas de Yoga", "Manaus", "Saúde e Bem-estar", "R$ 70,00 por aula", "Aulas de yoga para iniciantes e praticantes intermediários."),
            Item("Consultoria Financeira", "Goiânia", "Consultoria", "R$ 400,00", "Consultoria para planejamento financeiro pessoal ou empresarial."),
            Item("Pintura de Interiores", "Vitória", "Construção Civil", "R$ 1.200,00", "Pintura de paredes e tetos de ambientes internos."),
            Item("Tradução de Documentos", "Belém", "Tradução e Interpretação", "R$ 100,00 por página", "Tradução de documentos do português para o inglês e vice-versa."),
            Item("Serviço de Encanador", "João Pessoa", "Serviços Domésticos", "R$ 250,00", "Reparo e manutenção de encanamentos em residências."),
            Item("Consultoria de Marketing", "Maceió", "Consultoria", "R$ 800,00", "Consultoria para estratégias de marketing digital e tradicional."),
            Item("Aulas de Violão", "Campo Grande", "Educação", "R$ 60,00 por aula", "Aulas particulares de violão para todas as idades."),
            Item("Manutenção de Automóveis", "Aracaju", "Serviços Automotivos", "R$ 500,00", "Serviços de manutenção preventiva e corretiva de automóveis."),
            Item("Assistência Técnica de Celulares", "Cuiabá", "Tecnologia da Informação", "R$ 150,00", "Reparo de smartphones, incluindo troca de telas e baterias."),
            Item("Pet Sitting", "Palmas", "Cuidados com Animais", "R$ 80,00 por dia", "Cuidado de animais de estimação durante a ausência dos donos."),
            Item("Aulas de Dança", "São Luís", "Saúde e Bem-estar", "R$ 40,00 por aula", "Aulas de dança de diferentes estilos para todas as idades."),
            Item("Consultoria de Recursos Humanos", "Natal", "Consultoria", "R$ 1.000,00", "Consultoria em gestão de pessoas e recursos humanos."),
            Item("Manutenção de Piscinas", "Teresina", "Serviços Domésticos", "R$ 180,00", "Limpeza e manutenção de piscinas residenciais e comerciais."),
            Item("Aulas de Reforço Escolar", "Porto Velho", "Educação", "R$ 45,00 por hora", "Aulas particulares de reforço escolar para diversas disciplinas.")

        )
    }
    val items: LiveData<List<Item>> = _items
}

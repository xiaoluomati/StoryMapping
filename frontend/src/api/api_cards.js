import * as API from './api'

export default {

  getCardListTest: storyid => {
    return API.GET(`storycards/${storyid}`, )
  },

  getCardList: storyid => {
    return API.GET(`getCardList`, { 'storyId' : storyid })
  },

  addCard: (from, card) => {
    return API.POST(`addCard/${from}`, { 'storyId': card.storyId, 'title': card.title, 'content': card.content, 'state': card.state,
                                    'cost': card.cost, 'positionX': card.positionX,'positionY': card.positionY})
  },

  deleteCard: card => {
    return API.POST('deleteCard', {'positionX': card.positionX,'positionY': card.positionY, 'storyId': card.storyId})
  },

  updateCard: card => {
    return API.POST(`updateCard`, { 'storyId': card.storyId, 'title': card.title, 'content': card.content, 'state': card.state,
      'cost': card.cost, 'positionX': card.positionX,'positionY': card.positionY, 'cardId': card.cardId})
  }
}

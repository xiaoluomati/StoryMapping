import * as API from './api'

export default {

  getRelationList: cardId => {
    return API.GET(`storyRoles/getRelationList`, { 'cardId': cardId })
  },

  addRelation: relation => {
    return API.POST(`storyRoles/addRelation`, { 'cardId': relation.cardId, 'roleId': relation.roleId })
  },

  deleteRelation: relationId => {
    return API.DELETE(`storyRoles/deleteRelation`, { 'relationId': relationId })
  },

  getStoryRoleList: storyId => {
    return API.GET(`storyRoles/getStoryRoleList`, { 'storyId': storyId })
  },

  addStoryRole: role => {
    return API.POST(`storyRoles/addStoryRole`, { 'roleName': role.roleName, 'roleDetail': role.roleDetail, 'storyId': role.storyId })
  },

  deleteStoryRole: role => {
    return API.DELETE(`storyRoles/deleteStoryRole`, { 'roleId': role.roleId, 'storyId': role.storyId, 'roleName': '', 'roleDetail': '' })
  },

  updateStoryRole: role => {
    return API.PUT(`storyRoles/updateStoryRole`, { 'roleId': role.roleId, 'roleName': role.roleName, 'roleDetail': role.roleDetail, 'storyId': role.storyId })
  }
}

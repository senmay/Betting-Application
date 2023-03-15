# User

| Field | Meaning | POST(input) | POST(output) |
|-|-|-|-|
| id | Uuid automatically generated | - | M automatically generated id |
| username | login | M, NotEmpty, length > 4, unique in system, can't be on forbidden login list | M |
| e-mail | e-mail | M, NotEmpty, Unique, regex pattern | M |
| points | Points accumulated by correctly betting on match results | - | O |
| balance | How much money user has on account | default value 0 | M |
| userType | Automatically generated type of user. If user has ID other than 1, then every new user has userType "USER" | - | M |

# User Endpoints

| Endpoint | Meaning | Input | Output |
|-|-|-|-|
| /user - POST | Create a new user | M - assumptions like in User | - |
| /user - GET | Get all users from database | Users can only view their own user information or view information of other users if they have the proper permissions. | M - List of users represented as a User object |   
| /user/{id} - GET | Get user information by ID | M - id. User ID must exist in the system to retrieve user information. Users can only view their own user information or view information of other users if they have the proper permissions | M - User represented as a user object.|
| /user/admin - POST | Create a new user only if you have userType "ADMIN" | M - verification is done by header - if in header is usertype ADMIN then it allows to create new user | -
| /user/{id} - PUT | Update user from database | M - id, - usertype, O - rest of fields. User ID must exists in the system to update user information. User can only update their own information or update information of other users if they have the proper permissions. | M - id, username, email, balance, points, userType.
| /user/{id} - DEL | Delete user by ID | M - id. User ID must exists in the system to delete user information. User can only delete their own accout or delete other users if they have the proper permissions.| M - message which informs if deleting user was successful. Deleting user also will delete every bet associated with this user|

# Bet

|Field | Meaning | POST (input) | POST(output) |
|-|-|-|-|
| id | Uuid automatically generated | - | M automatically generated id |
| matchId | MatchId taken from matches database | M - matchId must exist in match database | M |
| userId | UserId taken from user database | M - userId must exist in match database | M |
| pointsFromBetEvent | How many points you can get for correctly predicitng match result | - | O - points are manually added by admin, default value is 0
| betType | The type of bet the user wants to place | M, one of the allowed bet types (WIN, LOSS, DRAW, OVER, UNDER) | M |
| betAmount | Ammount of money the user wants to bet | M - NotEmpty, minimum of 1$, maximum of 1000$ | M |

# Bet Endpoints

|Endpoint | Meaning | Input | Output |
|-|-|-|-|
| /bet - POST | Creating a new bet | M - assumptions like in Bet class. User can't have more than 1 bet placed on the same match. Can't create bet if (dateOfEvent + 5min) >= currentTime | - |

# Match
|Field | Meaning | Input | Output |
|-|-|-|-|
| id | Uuid automatically generated for the match | - | M -  automatically generated id |
| homeTeamId | Team Id taken from teams database | M - NotEmpty,TeamId must exist in team database. HomeTeamId can't be the same as AwayTeamId. | M |
| awayTeamId | Team Id taken from teams database | M - NotEmpty,TeamId must exist in team database. AwayTeamId can't be the same as HomeTeamId. | M |
| dateOfEvent | The date and time when the match is scheduled to take place, given in Central European Time (CET) | M - NotEmpty, date must be given in CET time. | M |
| oddsForHomeTeam | Odds generated for homeTeam taken from API | - | M - NotEmpty, odds are taken from outside API |
| oddsForAwayTeam | Odds generated for awayTeam taken from API | - | M - NotEmpty, odds are taken from outside API |
| isFinished | Indicates whether given match has ended | M | M |
| matchResultId | MatchResultId taken from matchResult database | O: Not empty, must be provided if the match is marked as finished in the input | O: Only present if the match is marked as finished in the output|

# Match Endpoints
|Endpoint | Meaning | Input | Output |
|-|-|-|-|
| /match - POST | Create a new user only if you have userType "ADMIN" | M - verification is done by header - if in header is usertype ADMIN then it allows to create new Match | - |
| /match - GET | Get all matches from database | - | M - A list of all matches in the database, each represented as a match object |
| /match/toBet - GET | Get all matches possible to bet from database | - | A list of all matches in the database that have not yet started and are not marked as finished, each represented as a match object
| /match/{id} - GET | Get match information with ID | M - NotEmpty, id must exist in Match database | Match object corresponding to given ID |
| /match/{id} - DEL | Delete match of given ID | M - NotEmpty, id must exist in Match Database, can be done only by Admin | - |

# Team
|Field | Meaning | Input | Output |
|-|-|-|-|
| id | uuid automatically generated for team | - | M - automatically generated id |
| name | Name of team | M - NotEmpty, length > 4, name of team without diacritical marks | M |

# Team Endpoints
|Endpoint | Meaning | Input | Output |
|-|-|-|-|
| /team - POST| Create a new team | Assumptions like in Team | - |
| /team - GET | Get all teams from database | - | M - A list of all teams in the database, each represented as a team object|
| /team/{id} - GET | Get team information with ID | M - NotEmpty, id must exist in Team database | Team object corresponding to given ID |
| /team/{id} - DEL | Delete team of given ID | M - NotEmpty, must exist in Team database| - |
| /team/{id} - PUT | Update team of given ID | M - id, name. Id must exist in Team database | - | 










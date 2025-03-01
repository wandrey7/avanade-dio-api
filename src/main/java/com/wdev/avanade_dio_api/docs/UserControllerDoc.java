
package com.wdev.avanade_dio_api.docs;

import com.wdev.avanade_dio_api.dto.UserDto;
import com.wdev.avanade_dio_api.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface UserControllerDoc {

    @Operation(
        summary = "Find user by ID",
        description = "Retrieves a user's complete profile including account, card, features, and news information"
    )
    @ApiResponse(
        responseCode = "200",
        description = "User found successfully",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = User.class),
            examples = @ExampleObject(value = """
                    {
                      "id": 1,
                      "name": "John Doe",
                      "account": {
                        "id": 1,
                        "number": "12345678",
                        "agency": "0001",
                        "balance": 1500.5,
                        "limit": 500
                      },
                      "card": {
                        "id": 1,
                        "number": "5555 6666 7777 8888",
                        "limit": 2000
                      },
                      "features": [
                        {
                          "id": 1,
                          "icon": "pix",
                          "description": "PIX payments"
                        },
                        {
                          "id": 2,
                          "icon": "loan",
                          "description": "Personal loan"
                        }
                      ],
                      "news": [
                        {
                          "id": 1,
                          "icon": "promotion",
                          "description": "New credit card with no annual fee"
                        }
                      ]
                    }
                    """)
        )
    )
    @ApiResponse(
        responseCode = "404",
        description = "User not found with the provided ID",
        content = @Content(
            mediaType = "application/json",
            examples = @ExampleObject(value = "{\"error\":\"Not Found\",\"message\":\"Resource ID not found\"}")
        )
    )
    ResponseEntity<UserDto> findById(@PathVariable Long id);

    @Operation(
        summary = "Create new user",
        description = "Creates a new user with account, card, features, and news information"
    )
    @RequestBody(
        description = "User information to create",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = User.class),
            examples = @ExampleObject(value = "{\"name\":\"John Doe\",...}")
        )
    )
    @ApiResponse(
        responseCode = "201",
        description = "User created successfully",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = User.class),
            examples = @ExampleObject(value = "{\"id\":1,\"name\":\"John Doe\",...}")
        )
    )
    @ApiResponse(
        responseCode = "422",
        description = "Invalid data provided",
        content = @Content(
            mediaType = "application/json",
            examples = @ExampleObject(value = "{\"error\":\"Unprocessable Entity\",\"message\":\"This Account number already exists\"}")
        )
    )
    ResponseEntity<UserDto> create(@RequestBody UserDto userDto);

    @Operation(
            summary = "Update existing user",
            description = "Updates a user's information including account, card, features, and news"
    )
    @RequestBody(
            description = "User information to update",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UserDto.class),
                    examples = @ExampleObject(value = """
                {
                  "name": "John Doe Updated",
                  "card": {
                    "number": "5555 6666 7777 8888"
                  },
                  "account": {
                    "balance": 2500.75
                  }
                }
                """)
            )
    )
    @ApiResponse(
            responseCode = "200",
            description = "User updated successfully",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UserDto.class),
                    examples = @ExampleObject(value = """
                {
                  "id": 1,
                  "name": "John Doe Updated",
                  "account": {
                    "id": 1,
                    "number": "12345678",
                    "agency": "0001",
                    "balance": 2500.75,
                    "limit": 500
                  },
                  "card": {
                    "id": 1,
                    "number": "5555 6666 7777 8888",
                    "limit": 2000
                  },
                  "features": [
                    {
                      "id": 1,
                      "icon": "pix",
                      "description": "PIX payments"
                    },
                    {
                      "id": 2,
                      "icon": "loan",
                      "description": "Personal loan"
                    }
                  ],
                  "news": [
                    {
                      "id": 1,
                      "icon": "promotion",
                      "description": "New credit card with no annual fee"
                    }
                  ]
                }
                """)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "User not found with the provided ID",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"error\":\"Not Found\",\"message\":\"Resource ID not found\"}")
            )
    )
    @ApiResponse(
            responseCode = "422",
            description = "Invalid data provided",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"error\":\"Unprocessable Entity\",\"message\":\"This Account number already exists\"}")
            )
    )
    ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto userDto);

    @Operation(
            summary = "Delete user by ID",
            description = "Deletes a user and all associated data (account, card, features, and news)"
    )
    @ApiResponse(
            responseCode = "204",
            description = "User deleted successfully",
            content = @Content
    )
    @ApiResponse(
            responseCode = "404",
            description = "User not found with the provided ID",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"error\":\"Not Found\",\"message\":\"Resource ID not found\"}")
            )
    )
    ResponseEntity<Void> delete(@PathVariable Long id);
}



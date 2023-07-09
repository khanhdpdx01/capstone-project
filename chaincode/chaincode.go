package main

import (
	"encoding/json"
	"fmt"
	"github.com/hyperledger/fabric-contract-api-go/contractapi"
)

type SmartContract struct {
	contractapi.Contract
}

type Diary struct {
	DiaryId   string `json:"diaryId"`
	DiaryName  string `json:"diaryName"`
	ProductName  string `json:"productName"`
	Status  string `json:"status"`
}

type DiaryDetail struct {
	DiaryDetailId   string `json:"diaryDetailId"`
	DiaryId  string `json:"diaryId"`
	IngredientName string `json:"ingredientName"`
	StepName  string `json:"stepName"`
	Description  string `json:"description"`
	CreatedAt  string `json:"createdAt"`
	CreatedBy  string `json:"createdBy"`
}

func (s *SmartContract) CreateDiary(ctx contractapi.TransactionContextInterface, diaryId string, diaryName string, productName string, status string) (Diary, error) {
	diary := Diary{
		DiaryId: diaryId,
		DiaryName:  diaryName,
		ProductName:  productName,
		Status:  status,
	}

	diaryAsBytes, _ := json.Marshal(diary)

	return diary, ctx.GetStub().PutState(diaryId, diaryAsBytes)
}

func (s *SmartContract) CreateDiaryDetail(ctx contractapi.TransactionContextInterface, diaryDetailId string, diaryId string, ingredientName string, stepName string, description string, createdAt string, createdBy string) error {
		diaryDetail := DiaryDetail{
			DiaryDetailId: diaryDetailId,
			DiaryId: diaryId,
			IngredientName:  ingredientName,
			StepName: stepName,
			Description: description,
			CreatedAt: createdAt,
			CreatedBy: createdBy,
	}

	diaryDetailAsBytes, _ := json.Marshal(diaryDetail)

    return ctx.GetStub().PutState(diaryDetailId, diaryDetailAsBytes)
}

func main() {
	chaincode, err := contractapi.NewChaincode(new(SmartContract))
	if err != nil {
		fmt.Printf("Error create track and trace chaincode: %s", err.Error())
		return
	}

	// start chaincode server 
	if err := chaincode.Start(); err != nil {
		fmt.Printf("Error starting track and trace chaincode: %s", err.Error())
	}
}
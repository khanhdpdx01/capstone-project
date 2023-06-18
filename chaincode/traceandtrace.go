package main

import (
	"encoding/json"
	"fmt"
	"strings"
	"github.com/hyperledger/fabric-contract-api-go/contractapi"
)

type SmartContract struct {
	contractapi.Contract
}

type Partner struct {
	PartnerId   string `json:"partnerId"`
	PartnerName  string `json:"partnerName"`
	Address string `json:"address"`
	Email  string `json:"email"`
	Phone  string `json:"phone"`
}

type Ingredient struct {
	IngredientId   string `json:"ingredientId"`
	IngredientName  string `json:"ingredientName"`
	IngredientType int `json:"ingredientType"`
	GtinCode string `json:"gtinCode"`
	Status  int `json:"status"`
    Partner Partner `json:"partner"`
	Description  string `json:"description"`
	CreatedBy string `json:"createdBy"`
	CertificatePath string `json:"certificatePath"`
	LinkTT string `json:"linkTT"`
	CreatedAt string `json:"createdAt"`
	ImagePath string `json:"imagePath"`
}

type QueryIngredientResult struct {
	Key    string `json:"Key"`
	Record *Ingredient
}

type Diary struct {
	DiaryId   string `json:"diaryId"`
	DiaryName  string `json:"diaryName"`
	ProcessName string `json:"processName"`
	ProductName  string `json:"productName"`
	Status  int `json:"status"`
}

type QueryDiaryResult struct {
	Key    string `json:"Key"`
	Record *Diary
}

// type AdditionalField struct {
// 	AdditionalFieldId int `json:"additionalFieldId"`
// 	AdditionalFieldName string `json:"additionalFieldName"`
// 	AdditionalFieldDescription string `json:"additionalFieldDescription"`
// }

type DiaryDetail struct {
	ProductionNotebookDetailId   string `json:"productionNotebookDetailId"`
	ProductionNotebookId  string `json:"productionNotebookId"`
	StepId string `json:"stepId"`
	StepName  string `json:"stepName"`
	Note  string `json:"note"`
	CreatedDate  string `json:"createdDate"`
	CreatedBy  string `json:"createdBy"`
	Ingredients  string `json:"ingredients"`
	Images  string `json:"images"`
}

type QueryDiaryDetailResult struct {
	Key    string `json:"Key"`
	Record *DiaryDetail
}

type PackageProduct struct {
	PackageId   string `json:"packageId"`
	ProductName  string `json:"productName"`
	StampSeries string `json:"stampSeries"`
	CreatedBy string `json:"createdBy"`
	CreatedAt  string `json:"createdAt"`
	Location string `json:"location"`
}

type QueryPackageResult struct {
	Key    string `json:"Key"`
	Record *PackageProduct
}

type EndProduct struct {
	EndProductId   string `json:"endProductId"`
	StampSeries string `json:"stampSeries"`
	DiaryName  string `json:"diaryName"`
	CreatedBy string `json:"createdBy"`
	CreatedAt  string `json:"createdAt"`
	Location string `json:"location"`
}

type QueryEndProductResult struct {
	Key    string `json:"Key"`
	Record *EndProduct
}

type Delivery struct { 
	DeliveryId   string `json:"deliveryId"`
	// Package Package `json:"package"`
	// Partner  Partner `json:"partner"`
	DeliveryCreatedBy string `json:"deliveryCreatedBy"`
	DeliveryCreatedAt  string `json:"deliveryCreatedAt"`
	Status int `json:"status"`
	SendFrom string `json:"sendFrom"`
	SendTo string `json:"sendTo"`
	ReceiverName string `json:"receiverName"`
}

type QueryDeliveryResult struct {
	Key    string `json:"Key"`
	Record *Delivery
}



func (s *SmartContract) CreateIngredient(ctx contractapi.TransactionContextInterface, ingredientId string, ingredientName string, ingredientType int, gtinCode string, status int,
	partnerId string, partnerName string, address string, email string, phone string, description string, createdBy string, certificatePath string, linkTT string,
	createdAt string, imagePath string) error {
		partner := Partner{
			PartnerId: partnerId,
			PartnerName:  partnerName,
			Address: address,
			Email:  email,
			Phone:  phone,
		}
		ingredient := Ingredient{
			IngredientId: ingredientId,
			IngredientName:  ingredientName,
			IngredientType: ingredientType,
			GtinCode:  gtinCode,
			Status: status,
			Partner: partner,
			Description: description,
			CreatedBy: createdBy,
			CertificatePath: certificatePath,
			LinkTT: linkTT,
			CreatedAt: createdAt,
			ImagePath: imagePath,
	}

	ingredientAsBytes, _ := json.Marshal(ingredient)

	return ctx.GetStub().PutState(ingredientId, ingredientAsBytes)
}

func (s *SmartContract) QueryIngredientByListId(ctx contractapi.TransactionContextInterface, listIdInput string) ([]QueryIngredientResult, error) {

	results := []QueryIngredientResult{}

	listId := strings.Split(listIdInput, ",")

	for i := 0; i < len(listId); i++ {
		ingredient, err := s.QueryIngredient(ctx, listId[i])
		if err != nil {
			return nil, err
		}

		queryResult := QueryIngredientResult{Key: listId[i], Record: ingredient}
		results = append(results, queryResult)
	}

	return results, nil
}

func (s *SmartContract) QueryIngredient(ctx contractapi.TransactionContextInterface, ingredientId string) (*Ingredient, error) {
	ingredientAsBytes, err := ctx.GetStub().GetState(ingredientId)

	if err != nil {
		return nil, fmt.Errorf("Failed to read from world state. %s", err.Error())
	}

	if ingredientAsBytes == nil {
		return nil, fmt.Errorf("%s does not exist", ingredientId)
	}

	ingredient := new(Ingredient)
	_ = json.Unmarshal(ingredientAsBytes, ingredient)

	return ingredient, nil
}

func (s *SmartContract) QueryAllIngredients(ctx contractapi.TransactionContextInterface) ([]QueryIngredientResult, error) {
	startKey := "I0"
	endKey := "Iz"

	resultsIterator, err := ctx.GetStub().GetStateByRange(startKey, endKey)

	if err != nil {
		return nil, err
	}
	defer resultsIterator.Close()

	results := []QueryIngredientResult{}

	for resultsIterator.HasNext() {
		queryResponse, err := resultsIterator.Next()

		if err != nil {
			return nil, err
		}

		ingredient := new(Ingredient)
		_ = json.Unmarshal(queryResponse.Value, ingredient)

		queryResult := QueryIngredientResult{Key: queryResponse.Key, Record: ingredient}
		results = append(results, queryResult)
	}

	return results, nil
}

func (s *SmartContract) CreateDiary(ctx contractapi.TransactionContextInterface, diaryId string, diaryName string, processName string, productName string, status int) error {
		diary := Diary{
			DiaryId: diaryId,
			DiaryName:  diaryName,
			ProcessName: processName,
			ProductName:  productName,
			Status:  status,
		}

		diaryAsBytes, _ := json.Marshal(diary)

	return ctx.GetStub().PutState(diaryId, diaryAsBytes)
}

func (s *SmartContract) QueryDiary(ctx contractapi.TransactionContextInterface, diaryId string) (*Diary, error) {
	diaryAsBytes, err := ctx.GetStub().GetState(diaryId)

	if err != nil {
		return nil, fmt.Errorf("Failed to read from world state. %s", err.Error())
	}

	if diaryAsBytes == nil {
		return nil, fmt.Errorf("%s does not exist", diaryId)
	}

	diary := new(Diary)
	_ = json.Unmarshal(diaryAsBytes, diary)

	return diary, nil
}

func (s *SmartContract) QueryAllDiaries(ctx contractapi.TransactionContextInterface) ([]QueryDiaryResult, error) {
	startKey := "D0"
	endKey := "Dz"

	resultsIterator, err := ctx.GetStub().GetStateByRange(startKey, endKey)

	if err != nil {
		return nil, err
	}
	defer resultsIterator.Close()

	results := []QueryDiaryResult{}

	for resultsIterator.HasNext() {
		queryResponse, err := resultsIterator.Next()

		if err != nil {
			return nil, err
		}

		diary := new(Diary)
		_ = json.Unmarshal(queryResponse.Value, diary)

		queryResult := QueryDiaryResult{Key: queryResponse.Key, Record: diary}
		results = append(results, queryResult)
	}

	return results, nil
}

func (s *SmartContract) CreateDiaryDetail(ctx contractapi.TransactionContextInterface, productionNotebookDetailId string, productionNotebookId string, stepId string, stepName string, note string, createdDate string,
	createdBy string, ingredients string, images string) error {
		// diary := Diary {
		// 	DiaryId: diaryId,
		// 	DiaryName:  diaryName,
		// 	ProcessName: processName,
		// 	ProductName:  productName,
		// 	Status:  status,
		// }
		diaryDetail := DiaryDetail{
			ProductionNotebookDetailId: productionNotebookDetailId,
			ProductionNotebookId: productionNotebookId,
			StepId:  stepId,
			StepName: stepName,
			Note: note,
			CreatedDate: createdDate,
			CreatedBy: createdBy,
			Ingredients: ingredients,
			Images: images,
	}

	diaryDetailAsBytes, _ := json.Marshal(diaryDetail)

    return ctx.GetStub().PutState(productionNotebookDetailId, diaryDetailAsBytes)
}

func (s *SmartContract) QueryDiaryDetail(ctx contractapi.TransactionContextInterface, productionNotebookDetailId string) (*DiaryDetail, error) {
	diaryDetailAsBytes, err := ctx.GetStub().GetState(productionNotebookDetailId)

	if err != nil {
		return nil, fmt.Errorf("Failed to read from world state. %s", err.Error())
	}

	if diaryDetailAsBytes == nil {
		return nil, fmt.Errorf("%s does not exist", productionNotebookDetailId)
	}

	diaryDetail := new(DiaryDetail)
	_ = json.Unmarshal(diaryDetailAsBytes, diaryDetail)

	return diaryDetail, nil
}

func (s *SmartContract) QueryDiaryDetailByListId(ctx contractapi.TransactionContextInterface, listIdInput string) ([]QueryDiaryDetailResult, error) {

	results := []QueryDiaryDetailResult{}

	listId := strings.Split(listIdInput, ",")

	for i := 0; i < len(listId); i++ {
		diaryDetail, err := s.QueryDiaryDetail(ctx, listId[i])
		if err != nil {
			return nil, err
		}

		queryResult := QueryDiaryDetailResult{Key: listId[i], Record: diaryDetail}
		results = append(results, queryResult)
	}

	return results, nil
}

func (s *SmartContract) QueryDiaryDetailByProductionNotebookId(ctx contractapi.TransactionContextInterface, productionNotebookId string) ([]QueryDiaryDetailResult, error) {
	startKey := "DT0"
	endKey := "DTz"

	resultsIterator, err := ctx.GetStub().GetStateByRange(startKey, endKey)

	if err != nil {
		return nil, err
	}
	defer resultsIterator.Close()

	results := []QueryDiaryDetailResult{}

	for resultsIterator.HasNext() {
		queryResponse, err := resultsIterator.Next()

		if err != nil {
			return nil, err
		}

		diaryDetail := new(DiaryDetail)
		_ = json.Unmarshal(queryResponse.Value, diaryDetail)

		if productionNotebookId == diaryDetail.ProductionNotebookId {
			queryResult := QueryDiaryDetailResult{Key: queryResponse.Key, Record: diaryDetail}
			results = append(results, queryResult)
		}
	}

	return results, nil
}

func (s *SmartContract) QueryAllDiaryDetails(ctx contractapi.TransactionContextInterface) ([]QueryDiaryDetailResult, error) {
	startKey := "DT0"
	endKey := "DTz"

	resultsIterator, err := ctx.GetStub().GetStateByRange(startKey, endKey)

	if err != nil {
		return nil, err
	}
	defer resultsIterator.Close()

	results := []QueryDiaryDetailResult{}

	for resultsIterator.HasNext() {
		queryResponse, err := resultsIterator.Next()

		if err != nil {
			return nil, err
		}

		diaryDetail := new(DiaryDetail)
		_ = json.Unmarshal(queryResponse.Value, diaryDetail)

		queryResult := QueryDiaryDetailResult{Key: queryResponse.Key, Record: diaryDetail}
		results = append(results, queryResult)
	}

	return results, nil
}

func (s *SmartContract) CreatePackage(ctx contractapi.TransactionContextInterface, packageId string, productName string, stampSeries string, createdBy string, createdAt string, location string) error {
		packageProduct := PackageProduct{
			PackageId: packageId,
			ProductName:  productName,
			StampSeries: stampSeries,
			CreatedBy:  createdBy,
			CreatedAt:  createdAt,
			Location:  location,
		}

	packageAsBytes, _ := json.Marshal(packageProduct)

    return ctx.GetStub().PutState(packageId, packageAsBytes)
}

func (s *SmartContract) QueryPackage(ctx contractapi.TransactionContextInterface, packageId string) (*PackageProduct, error) {
	packageAsBytes, err := ctx.GetStub().GetState(packageId)

	if err != nil {
		return nil, fmt.Errorf("Failed to read from world state. %s", err.Error())
	}

	if packageAsBytes == nil {
		return nil, fmt.Errorf("%s does not exist", packageId)
	}

	packageProduct := new(PackageProduct)
	_ = json.Unmarshal(packageAsBytes, packageProduct)

	return packageProduct, nil
}

func (s *SmartContract) QueryAllPackages(ctx contractapi.TransactionContextInterface) ([]QueryPackageResult, error) {
	startKey := "P0"
	endKey := "Pz"

	resultsIterator, err := ctx.GetStub().GetStateByRange(startKey, endKey)

	if err != nil {
		return nil, err
	}
	defer resultsIterator.Close()

	results := []QueryPackageResult{}

	for resultsIterator.HasNext() {
		queryResponse, err := resultsIterator.Next()

		if err != nil {
			return nil, err
		}

		packageProduct := new(PackageProduct)
		_ = json.Unmarshal(queryResponse.Value, packageProduct)

		queryResult := QueryPackageResult{Key: queryResponse.Key, Record: packageProduct}
		results = append(results, queryResult)
	}

	return results, nil
}

func (s *SmartContract) CreateEndProduct(ctx contractapi.TransactionContextInterface, endProductId string, stampSeries string, diaryName string, createdBy string, createdAt string, location string) error {
	endProduct := EndProduct{
		EndProductId: endProductId,
		StampSeries:  stampSeries,
		DiaryName: diaryName,
		CreatedBy:  createdBy,
		CreatedAt:  createdAt,
		Location:  location,
	}

    endProductAsBytes, _ := json.Marshal(endProduct)

    return ctx.GetStub().PutState(endProductId, endProductAsBytes)
}

func (s *SmartContract) QueryEndProduct(ctx contractapi.TransactionContextInterface, endProductId string) (*EndProduct, error) {
	endProductAsBytes, err := ctx.GetStub().GetState(endProductId)

	if err != nil {
		return nil, fmt.Errorf("Failed to read from world state. %s", err.Error())
	}

	if endProductAsBytes == nil {
		return nil, fmt.Errorf("%s does not exist", endProductId)
	}

	endProduct := new(EndProduct)
	_ = json.Unmarshal(endProductAsBytes, endProduct)

	return endProduct, nil
}

func (s *SmartContract) QueryAllEndProducts(ctx contractapi.TransactionContextInterface) ([]QueryEndProductResult, error) {
	startKey := "EP0"
	endKey := "EPz"

	resultsIterator, err := ctx.GetStub().GetStateByRange(startKey, endKey)

	if err != nil {
		return nil, err
	}
	defer resultsIterator.Close()

	results := []QueryEndProductResult{}

	for resultsIterator.HasNext() {
		queryResponse, err := resultsIterator.Next()

		if err != nil {
			return nil, err
		}

		endProduct := new(EndProduct)
		_ = json.Unmarshal(queryResponse.Value, endProduct)

		queryResult := QueryEndProductResult{Key: queryResponse.Key, Record: endProduct}
		results = append(results, queryResult)
	}

	return results, nil
}

func (s *SmartContract) CreateDelivery(ctx contractapi.TransactionContextInterface, deliveryId string, deliveryCreatedBy string, deliveryCreatedAt string, status int, sendFrom string, sendTo string, receiverName string) error {
		// packageProduct := PackageProduct{
		// 	PackageId: packageId,
		// 	ProductName:  productName,
		// 	StampSeries: stampSeries,
		// 	CreatedBy:  createdBy,
		// 	CreatedAt:  createdAt,
		// 	Location:  location,
		// }

		// partner := Partner{
		// 	PartnerId: partnerId,
		// 	PartnerName:  partnerName,
		// 	Address: address,
		// 	Email:  email,
		// 	Phone:  phone,
		// }

		delivery := Delivery{
			DeliveryId: deliveryId,
			// PackageProduct: packageProduct,
			// Partner: partner,
			DeliveryCreatedBy: deliveryCreatedBy,
			DeliveryCreatedAt: deliveryCreatedAt,
			Status: status,
			SendFrom: sendFrom,
			SendTo: sendTo,
			ReceiverName: receiverName,
		}

		deliveryAsBytes, _ := json.Marshal(delivery)

    return ctx.GetStub().PutState(deliveryId, deliveryAsBytes)
}

func (s *SmartContract) QueryDelivery(ctx contractapi.TransactionContextInterface, deliveryId string) (*Delivery, error) {
	deliveryAsBytes, err := ctx.GetStub().GetState(deliveryId)

	if err != nil {
		return nil, fmt.Errorf("Failed to read from world state. %s", err.Error())
	}

	if deliveryAsBytes == nil {
		return nil, fmt.Errorf("%s does not exist", deliveryId)
	}

	delivery := new(Delivery)
	_ = json.Unmarshal(deliveryAsBytes, delivery)

	return delivery, nil
}

func (s *SmartContract) QueryAllDeliveries(ctx contractapi.TransactionContextInterface) ([]QueryDeliveryResult, error) {
	startKey := "DL0"
	endKey := "DLz"

	resultsIterator, err := ctx.GetStub().GetStateByRange(startKey, endKey)

	if err != nil {
		return nil, err
	}
	defer resultsIterator.Close()

	results := []QueryDeliveryResult{}

	for resultsIterator.HasNext() {
		queryResponse, err := resultsIterator.Next()

		if err != nil {
			return nil, err
		}

		delivery := new(Delivery)
		_ = json.Unmarshal(queryResponse.Value, delivery)

		queryResult := QueryDeliveryResult{Key: queryResponse.Key, Record: delivery}
		results = append(results, queryResult)
	}

	return results, nil
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


